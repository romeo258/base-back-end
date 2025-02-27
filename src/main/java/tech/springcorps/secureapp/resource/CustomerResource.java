package tech.springcorps.secureapp.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tech.springcorps.secureapp.domain.Customer;
import tech.springcorps.secureapp.domain.HttpResponse;
import tech.springcorps.secureapp.domain.Invoice;
import tech.springcorps.secureapp.dto.UserDTO;
import tech.springcorps.secureapp.report.CustomerReport;
import tech.springcorps.secureapp.service.CustomerService;
import tech.springcorps.secureapp.service.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.net.URI;

import static java.time.LocalTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.parseMediaType;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
public class CustomerResource {
    private final CustomerService customerService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> getCustomers(@AuthenticationPrincipal UserDTO user, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "page", customerService.getCustomers(page.orElse(0), size.orElse(10)),
                                "stats", customerService.getStats()))
                        .message("Customers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createCustomer(@AuthenticationPrincipal UserDTO user, @RequestBody Customer customer) {
        return ResponseEntity.created(URI.create(""))
                .body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .data(of("user", userService.getUserByEmail(user.getEmail()),
                                        "customer", customerService.createCustomer(customer)))
                                .message("Customer created")
                                .status(CREATED)
                                .statusCode(CREATED.value())
                                .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HttpResponse> getCustomer(@AuthenticationPrincipal UserDTO user, @PathVariable("id") Long id) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "customer", customerService.getCustomer(id)))
                        .message("Customer retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/search")
    public ResponseEntity<HttpResponse> searchCustomer(@AuthenticationPrincipal UserDTO user, Optional<String> name, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "page", customerService.searchCustomers(name.orElse(""), page.orElse(0), size.orElse(10))))
                        .message("Customers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateCustomer(@AuthenticationPrincipal UserDTO user, @RequestBody Customer customer) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "customer", customerService.updateCustomer(customer)))
                        .message("Customer updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/invoice/created")
    public ResponseEntity<HttpResponse> createInvoice(@AuthenticationPrincipal UserDTO user, @RequestBody Invoice invoice) {
        return ResponseEntity.created(URI.create(""))
                .body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .data(of("user", userService.getUserByEmail(user.getEmail()),
                                        "invoice", customerService.createInvoice(invoice)))
                                .message("Invoice created")
                                .status(CREATED)
                                .statusCode(CREATED.value())
                                .build());
    }

    @GetMapping("/invoice/new")
    public ResponseEntity<HttpResponse> newInvoice(@AuthenticationPrincipal UserDTO user) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "customers", customerService.getCustomers()))
                        .message("Customers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/invoice/list")
    public ResponseEntity<HttpResponse> getInvoices(@AuthenticationPrincipal UserDTO user, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "page", customerService.getInvoices(page.orElse(0), size.orElse(10))))
                        .message("Invoice retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/invoice/get/{id}")
    public ResponseEntity<HttpResponse> getInvoice(@AuthenticationPrincipal UserDTO user, @PathVariable("id") Long id) {
        Invoice invoice = customerService.getInvoice(id);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "invoice", invoice, "customer", customerService.getInvoice(id).getCustomer()))
                        .message("Invoice retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping("/invoice/addtocustomer/{id}")
    public ResponseEntity<HttpResponse> addInvoiceToCustomer(@AuthenticationPrincipal UserDTO user, @PathVariable("id") Long id, @RequestBody Invoice invoice) {
        customerService.addInvoiceToCustomer(id, invoice);
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user", userService.getUserByEmail(user.getEmail()),
                                "customers", customerService.getCustomers()))
                        .message(String.format("Invoice added to customer with ID: %s", id))
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/download/report")
    public ResponseEntity<Resource> downloadReport() {
        List<Customer> customers = new ArrayList<>();
        customerService.getCustomers().iterator().forEachRemaining(customers::add);
        CustomerReport report = new CustomerReport(customers);
        HttpHeaders headers = new HttpHeaders();
        headers.add("File-Name", "customer-report.xlsx");
        headers.add(CONTENT_DISPOSITION, "attachment;File-Name=customer-report.xlsx");
        return ResponseEntity.ok().contentType(parseMediaType("application/vnd.ms-excel"))
                .headers(headers).body(report.export());
    }
}