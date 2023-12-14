package com.CinemaProject.cinemaProject.reservation;

import com.CinemaProject.cinemaProject.reservation.domain.ReservationFacade;
import com.CinemaProject.cinemaProject.reservation.domain.Status;
import com.CinemaProject.cinemaProject.reservation.dto.CreateReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.ReservationDto;
import com.CinemaProject.cinemaProject.reservation.dto.UpdateReservationDto;
import com.CinemaProject.cinemaProject.utils.TaskListService;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskList;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.ZeebeClient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reservation")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationController {
    ReservationFacade reservationFacade;
    private TaskListService taskListService;
    @Qualifier("zeebeClientLifecycle")
    private ZeebeClient zeebeClient;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    @PostMapping("/start")
    public void startProcessInstance(@RequestBody Map<String,Object> variables) {

        System.out.println("Process Started" + variables);

        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("cinema-reservation-process")
                .latestVersion()
                .variables(variables)
                .send();



    }

    @GetMapping("/confirmPayment")
    public ResponseEntity<TaskList> getAllExamApplications(@RequestParam(required = false) String reservationId) {

        TaskList tasks = new TaskList();

        try {
            tasks = taskListService.getTaskList(TaskState.CREATED, null);

            System.out.println(tasks);

            /*for(Task task : tasks) {
                this.completeTask(task.getId(), Map.of("paymentMade", "yes"));
            }*/

        } catch (Exception e) {

        }

        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<ReservationDto> createMovie(@RequestBody CreateReservationDto reservation) {
        return ResponseEntity.ok(reservationFacade.createReservation(reservation));
    }

    @GetMapping("/all")
    ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.ok(reservationFacade.getAllReservation());
    }

    @PatchMapping("/reserved/{reservationId}")
    ResponseEntity<ReservationDto> updateStatus(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationFacade.updateStatus(reservationId, Status.RESERVED));
    }

    @PatchMapping("/reservationDeclined/{reservationId}")
    ResponseEntity<UpdateReservationDto> setReservationDeclined(@PathVariable UUID reservationId) {
        return ResponseEntity.ok(reservationFacade.setReservationDeclined(reservationId));
    }



    @PostMapping("/makePayment/{taskId}/{reservationId}")
    public void completeTask(@PathVariable String taskId, @PathVariable String reservationId)
            throws TaskListException {
            Map<String,Object> variables = new HashMap<>();
            System.out.println(variables);
            variables.put("paymentMade", true);
        System.out.println("WORKING");

        reservationFacade.updateStatus(UUID.fromString(reservationId), Status.PAYMENT_MADE);
        taskListService.completeTask(taskId, variables);
    }

}
