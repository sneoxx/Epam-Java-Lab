package com.zaraev.epam.javacourses.demonstration;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class WorkDemonstrationSpringCoreCustomerTest {

//    @InjectMocks
//    WorkDemonstrationCustomer workDemonstrationCustomer;


    @Mock
    Customer customer = new Customer();

    @Test
    public void workDemonstrationCustomer() {

        WorkDemonstrationCustomer workDemonstrationCustomer = mock(WorkDemonstrationCustomer.class);

       // doNothing().when(workDemonstrationCustomer).testCustomer();

     //   workDemonstrationCustomer.testCustomer();

       // verify(workDemonstrationCustomer, times(1)).testCustomer();
    //     verify(workDemonstrationCustomer, times(1)).serviceEntity.deleteCustomer(null);
       //  assertEquals


//        doNothing().when(workDemonstrationCustomer).testCustomer();
//
//        int param = 10;
//        int mockedValue = 25;
//        // mock void method
//        Mockito.doNothing().when(valueRepository).reset();
//        // mock method with returning value
//        Mockito.when(valueRepository.get(param)).thenReturn(mockedValue);
    }
}