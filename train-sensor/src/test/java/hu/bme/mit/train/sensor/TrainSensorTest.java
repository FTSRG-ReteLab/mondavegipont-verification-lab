package hu.bme.mit.train.sensor;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainController mockedTrainController;
	TrainUser mockedTrainUser;
	TrainSensorImpl trainSensor;

    @Before
    public void before() {
    	 mockedTrainController = mock(TrainController.class);
    	 mockedTrainUser = mock(TrainUser.class);
    	
    	 trainSensor = new TrainSensorImpl(mockedTrainController, mockedTrainUser);
    }

    @Test
    public void over500Test()
    {
       trainSensor.overrideSpeedLimit(501);
       verify(mockedTrainUser, times(1)).setAlarmState(true);    }
    
    @Test
    public void lover0Test()
    {
       trainSensor.overrideSpeedLimit(-5);
       verify(mockedTrainUser, times(1)).setAlarmState(true);    }
    
    @Test
    public void test49percent()
    {
    	when(mockedTrainController.getReferenceSpeed()).thenReturn(100);
       trainSensor.overrideSpeedLimit(49);
       verify(mockedTrainUser, times(1)).setAlarmState(true);
    }
    
    @Test
    public void test90percent()
    {
    	when(mockedTrainController.getReferenceSpeed()).thenReturn(100);
       trainSensor.overrideSpeedLimit(90);
       verify(mockedTrainUser, times(1)).setAlarmState(false);
    }
}
