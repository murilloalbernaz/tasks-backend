package br.ce.wcaquino.taskbackend.utils;



import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;



public class DateUtilsTest {
    @Test
    public void deveRetornarTrueDataFuturas(){
        LocalDate date = LocalDate.of(2021,9,06);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void deveRetornarTrueDataIgual(){
        LocalDate date = LocalDate.now();
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void deveRetornarFalseDataPassada(){
        LocalDate date = LocalDate.of(2020, 8, 20);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }

}