package com.pathshala.controller;

import com.pathshala.dto.AssignmentDTO;
import com.pathshala.enums.UserType;
import com.pathshala.service.AssignmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AssignmentControllerTests {

    @Mock
    private AssignmentService assignmentService;

    private AssignmentController assignmentController;

    @BeforeEach
    void setup(){
        assignmentController = new AssignmentController(assignmentService);
    }

    @AfterEach
    void tearDown(){
        assignmentController = null;
    }
    @Test
    void testFindAllAssignmentsWhenRecordExists(){
        Mockito.when(assignmentService.findAll()).thenReturn(assignmentDTOList());
        //Mockito.when(assignmentService.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<AssignmentDTO>> assignmentListResponse = assignmentController.findAll();
        //check for has body
        Assertions.assertTrue(assignmentListResponse.hasBody());
        Assertions.assertEquals(assignmentListResponse.getStatusCode(), HttpStatus.OK);
        List<AssignmentDTO> assignmentList = assignmentListResponse.getBody();
        //test for null/not null
        assert assignmentList != null;
        Assertions.assertEquals(assignmentList.size(), 2);
        Assertions.assertEquals(assignmentList.get(0).getId(), 1L);
    }

    @Test
    void testFindAllAssignmentsWhenRecordDoesNotExists(){
        // Mocking the behavior of the assignmentService.findAll() when no records exist
        Mockito.when(assignmentService.findAll()).thenReturn(new ArrayList<>());

        // Invoking the controller method
        ResponseEntity<List<AssignmentDTO>> assignmentListResponse = assignmentController.findAll();

        // Asserting the response
        Assertions.assertTrue(assignmentListResponse.hasBody());
        Assertions.assertEquals(assignmentListResponse.getStatusCode(), HttpStatus.OK);

        // Checking that the returned list is empty
        List<AssignmentDTO> assignmentList = assignmentListResponse.getBody();
        // Check that the returned list is not null
        Assertions.assertNotNull(assignmentList);
        Assertions.assertTrue(assignmentList.isEmpty());


    }


    private List<AssignmentDTO> assignmentDTOList(){
        // Create a random Date object
        Date date = new Date();
        // Create a Timestamp object from the Date object
        Timestamp timestamp = new Timestamp(date.getTime());

        List<AssignmentDTO> res = new ArrayList<>();
        res.add(new AssignmentDTO(1L, "Test1", "Test11", timestamp , 100.00f , "/testFilePath1", 1L,"test1", UserType.ADMIN));
        res.add(new AssignmentDTO(2L, "Test2", "Test22", timestamp , 200.00f , "/testFilePath2", 1L, "test1", UserType.ADMIN));
        return res;
    }

}
