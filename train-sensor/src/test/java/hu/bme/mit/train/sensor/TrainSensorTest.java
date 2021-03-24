package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController trainController;
    TrainUserImpl trainUser;
    TrainSensorImpl trainSensor;

    @Before
    public void before() {
        trainUser = mock(TrainUserImpl.class);
        trainController = mock(TrainController.class);
        trainSensor = new TrainSensorImpl(trainController, trainUser);
        when(trainController.getReferenceSpeed()).thenReturn(150);
    }

    @Test
    public void NewSpeedLimitUnderAbsoluteMargin() {
        trainSensor.overrideSpeedLimit(-5);
        verify(trainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void NewSpeedLimitOverAbsoluteMargin() {
        trainSensor.overrideSpeedLimit(550);
        verify(trainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void NewSpeedLimitAlarmRelativeMargin(){
        trainSensor.overrideSpeedLimit(2);
        verify(trainUser, times(1)).setAlarmState(true);
    }

    @Test
    public void NewSpeedLimitIsCorrect(){
        when(trainController.getReferenceSpeed()).thenReturn(150);
        trainSensor.overrideSpeedLimit(200);
        verify(trainUser, times(0)).setAlarmState(false);
    }
}
