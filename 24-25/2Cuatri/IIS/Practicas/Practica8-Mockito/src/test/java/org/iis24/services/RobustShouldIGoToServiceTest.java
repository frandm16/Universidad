package org.iis24.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Francisco Dorado Maldonado

class RobustShouldIGoToServiceTest {
    private final int MAX_WS=5;
    private final int MAX_MS=5;

    LinkedList<IWeatherService> ws;
    LinkedList<IMapService> ms;
    ShouldIGoToService service;

    @BeforeEach
    void setUp() {
        ws = new LinkedList<IWeatherService>();
        for (int i=1; i<=MAX_WS; i++) {
            ws.add(mock(IWeatherService.class));
        }
        ms = new LinkedList<IMapService>();
        for (int i=1; i<=MAX_MS; i++) {
            ms.add(mock(IMapService.class));
        }
        service = new RobustShouldIGoToService(ms,ws);
    }
    @AfterEach
    void tearDown() {
        service = null;
        ws = null;
        ms = null;
    }

    @Test
    void shouldRaiseInvalidServiceInstanceExceptionWhenListsContainNullReferences(){
        ms.set(3,null);

        assertThrows(InvalidServiceInstanceException.class,() -> service= new RobustShouldIGoToService(ms,ws));

    }

    @Test
    void shouldRaiseInvalidMapServiceExecutionExceptionIfMapServiceFails(){
        ms.set(1,null);

        assertThrows(InvalidMapServiceExecutionException.class,() -> service.shouldIGoTo("Malaga",LocalDate.now()));

    }

    @Test
    void shouldInvokeWeatherServiceWithValidCoordinatesIfOneMapServiceWorksWell() {

        GPSCoordinates GPSCoords = new GPSCoordinates(10.0, 10.0);
        when(ms.getLast().getCoordinates("Malaga")).thenReturn(GPSCoords);
        service.setRainAmountThreshold(50);
        service.setRainProbabilityThreshold(50);

        service.shouldIGoTo("Malaga", LocalDate.now());

        verify(ws.getFirst(),times(1)).rainProbability(GPSCoords, LocalDate.now());
        verify(ws.getFirst(),times(1)).totalAmountOfRain(GPSCoords,LocalDate.now());
    }

    @Test
    void shouldReturnTrueWhenAverageOfProbabilitiesAndTotalAmountOfRainAreZero(){
        GPSCoordinates GPSCoords = new GPSCoordinates(10.0, 10.0);
        when(ms.getLast().getCoordinates("Malaga")).thenReturn(GPSCoords);
        LocalDate today= LocalDate.now();
        for(int i=0;i<MAX_WS;i++){
            when(ws.get(i).rainProbability(GPSCoords,today)).thenReturn(0.0);
            when(ws.get(i).totalAmountOfRain(GPSCoords,today)).thenReturn(0.0);
        }
        service.setRainAmountThreshold(50);
        service.setRainProbabilityThreshold(50);

        boolean result= service.shouldIGoTo("Malaga",today);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenAverageOfProbabilitiesAndTotalAmountOfRainAreEqualToThresh(){
        GPSCoordinates GPSCoords = new GPSCoordinates(10.0, 10.0);
        when(ms.getLast().getCoordinates("Malaga")).thenReturn(GPSCoords);
        LocalDate today= LocalDate.now();
        for(int i=0;i<MAX_WS;i++){
            when(ws.get(i).rainProbability(GPSCoords,today)).thenReturn(20.0);
            when(ws.get(i).totalAmountOfRain(GPSCoords,today)).thenReturn(20.0);
        }
        service.setRainAmountThreshold(20);
        service.setRainProbabilityThreshold(20);

        boolean result= service.shouldIGoTo("Malaga",today);

        assertFalse(result);
    }

    @Test
    void shouldInvokeAllAvailableWeatherServices() {

        GPSCoordinates GPSCoords = new GPSCoordinates(10.0, 10.0);
        when(ms.getLast().getCoordinates("Malaga")).thenReturn(GPSCoords);
        service.setRainAmountThreshold(50);
        service.setRainProbabilityThreshold(50);

        service.shouldIGoTo("Malaga", LocalDate.now());

        for (int i=0;i<MAX_WS;i++){
            verify(ws.get(i),times(1)).rainProbability(GPSCoords, LocalDate.now());
            verify(ws.get(i),times(1)).totalAmountOfRain(GPSCoords,LocalDate.now());
        }

    }

}
