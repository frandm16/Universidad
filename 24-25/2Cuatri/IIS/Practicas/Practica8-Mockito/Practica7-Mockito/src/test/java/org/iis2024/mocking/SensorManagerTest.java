package org.iis2024.mocking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorManagerTest {
    private final int MAXIMUM_NUMBER_OF_SENSORS = 100;
    SensorManager sl;


    @BeforeEach
    void setUp(){
        sl= new SensorManager();

    }

    @AfterEach
    void tearDown(){
        sl= null;
    }
    @Test
    void InitiallyNumberOfSensorsEqualsZero(){
        assertEquals(0,sl.getNumberOfSensors());
    }
    @Test
    void NoSensorsRegisteredAndDeleteOneElevatesError(){
        String nombre="Falso";

        
        assertThrows(SensorException.class,() -> sl.deleteSensor(nombre));
    }
    @Test
    void NoSensorsRegisteredAndAddsOneVerify() throws SensorException {
        TemperatureSensor sensor = mock(TemperatureSensor.class);
        sl.registerSensor(sensor);


        assertEquals(1,sl.getNumberOfSensors());
    }
    @Test
    void TwoSensorsDeleteNonExistentElevatesError() throws SensorException{
        TemperatureSensor sensor1 = mock(TemperatureSensor.class);
        when(sensor1.getName()).thenReturn("sensor1");
        sl.registerSensor(sensor1);

        TemperatureSensor sensor2 = mock(TemperatureSensor.class);
        when(sensor2.getName()).thenReturn("sensor2");
        sl.registerSensor(sensor2);

        String nombre="Inexistente";


        assertThrows(SensorException.class,() ->sl.deleteSensor(nombre));
        verify(sensor1,times(1)).getName();
        verify(sensor2,times(1)).getName();
    }
    @Test
    void TempMediaCorrecta() throws SensorException {
        TemperatureSensor sensor = mock(TemperatureSensor.class);
        when(sensor.isAvailable()).thenReturn(true);
        when(sensor.getTemperatureCelsius()).thenReturn(25.0);
        sl.registerSensor(sensor);

        assertEquals(25.0,sl.getAverageTemperature());
        verify(sensor,times(1)).isAvailable();
        verify(sensor,times(1)).getTemperatureCelsius();
    }

    @Test
    void TempMediaCorrectaDeTres() throws SensorException {
        TemperatureSensor sensor1 = mock(TemperatureSensor.class);
        when(sensor1.isAvailable()).thenReturn(true);
        when(sensor1.getTemperatureCelsius()).thenReturn(25.0);
        sl.registerSensor(sensor1);

        TemperatureSensor sensor2 = mock(TemperatureSensor.class);
        when(sensor2.isAvailable()).thenReturn(true);
        when(sensor2.getTemperatureCelsius()).thenReturn(0.0);
        sl.registerSensor(sensor2);

        TemperatureSensor sensor3 = mock(TemperatureSensor.class);
        when(sensor3.isAvailable()).thenReturn(true);
        when(sensor3.getTemperatureCelsius()).thenReturn(50.0);
        sl.registerSensor(sensor3);

        assertEquals(25.0,sl.getAverageTemperature());
        verify(sensor1,times(0)).getName();
    }

    @Test
    void TempMediaCorrectaDeDos() throws SensorException {
        TemperatureSensor sensor1 = mock(TemperatureSensor.class);
        when(sensor1.isAvailable()).thenReturn(true);
        when(sensor1.getTemperatureCelsius()).thenReturn(25.0);
        sl.registerSensor(sensor1);

        TemperatureSensor sensor2 = mock(TemperatureSensor.class);
        when(sensor2.isAvailable()).thenReturn(true);
        when(sensor2.getTemperatureCelsius()).thenReturn(0.0);
        sl.registerSensor(sensor2);

        TemperatureSensor sensor3 = mock(TemperatureSensor.class);
        when(sensor3.isAvailable()).thenReturn(false);
        when(sensor3.getTemperatureCelsius()).thenReturn(50.0);
        sl.registerSensor(sensor3);

        assertEquals(12.5,sl.getAverageTemperature());
        verify(sensor3,times(1)).isAvailable();
        verify(sensor3,times(0)).getTemperatureCelsius();
    }

    @Test
    void BorrarSensorDecrementaEnUno() throws SensorException {
        TemperatureSensor sensor = mock(TemperatureSensor.class);
        when(sensor.getName()).thenReturn("sensor");
        sl.registerSensor(sensor);
        int aux=sl.getNumberOfSensors();

        sl.deleteSensor("sensor");

        assertEquals(aux-1,sl.getNumberOfSensors());
        verify(sensor,atLeast(1)).getName();
    }

    @Test
    void ElevaErrorEnUnaListaLlena() throws SensorException {
        for(int i=0;i<MAXIMUM_NUMBER_OF_SENSORS;i++){
            TemperatureSensor sensor = mock(TemperatureSensor.class);
            sl.registerSensor(sensor);
        }
        TemperatureSensor sensorNuevo = mock(TemperatureSensor.class);

        assertThrows(SensorException.class,() ->sl.registerSensor(sensorNuevo));
    }

    @Test
    void LLamadoCuatroVecesSeElimina() throws SensorException {
        TemperatureSensor sensor = mock(TemperatureSensor.class);
        sl.registerSensor(sensor);
        when(sensor.isAvailable()).thenReturn(true);
        sl.contactSensors();
        when(sensor.isAvailable()).thenReturn(false);
        sl.contactSensors();
        when(sensor.isAvailable()).thenReturn(false);
        sl.contactSensors();
        when(sensor.isAvailable()).thenReturn(false);
        sl.contactSensors();

        assertEquals(0,sl.getNumberOfSensors());
        verify(sensor,times(4)).isAvailable();
    }
}
