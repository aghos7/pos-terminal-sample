package com.sample.pointofsale;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Run all tests
 * @author Lucas Anderson 
 */
@RunWith(Suite.class)
@SuiteClasses({ TestMiscSimplePointOfSaleTerminal.class, TestParameterizedPointOfSaleTerminal.class })
public class TestAll { }
