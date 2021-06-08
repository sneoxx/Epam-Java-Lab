package com.zaraev.epam.javacourses.resource;

import com.zaraev.epam.javacourses.dto.CustomerDTO;
import com.zaraev.epam.javacourses.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс для обработки веб запросов к Customer
 */
@RestController
@Slf4j
public class CustomerResourceImpl implements CustomerResource {

    @Autowired
    private CustomerService customerService;

    /**
     * Получение клиента по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public CustomerDTO get(@PathVariable("id") int id) {
        log.info("get() - Получен customer по id {}", id);
        return customerService.getCustomer(id);
    }

    /**
     * Получение всех клиентов
     */
    @Override
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<CustomerDTO> getAll() {
        log.info("getAll()- Получены все customer");
        return customerService.getAllCustomer();
    }

    /**
     * Создание нового клиента из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
        log.info("create() - Создан новый customer {}", customerDTO);
        return customerService.create(customerDTO);
    }

    /**
     * Обновление полей клиента из переданного json в запросе
     */
    @Override
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    public CustomerDTO update(@PathVariable("id") int id, @RequestBody CustomerDTO customerDTO) {
        log.info("update() - Обновлен customer c id {}", id);
        return customerService.update(id, customerDTO);
    }

    /**
     * Удаление клиента по id переданного в запросе
     */
    @Override
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public CustomerDTO delete(@PathVariable("id") int id) {
        log.info("delete() - Удален customer с id {}", id);
        return customerService.deleteById(id);
    }
}

//    /**
//     * Создание нового товара из переданного json в запросе
//     */
//    @RequestMapping(value="/customer", method= RequestMethod.POST)
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        var customerDTO = gson.fromJson(servletsHelper.parseJsonToString(req), CustomerDTO.class);
//        var customerCheck = customerService.create(customerDTO);
//        var jsonString = this.gson.toJson(customerCheck);
//        servletsHelper.printJson(jsonString, resp);
//    }
//
//    /**
//     * Обновление полей товара из переданного json в запросе
//     */
//    @RequestMapping(value="/customer", method= RequestMethod.PUT)
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        var id = servletsHelper.validateAndGetIdFromRequest(req);
//        if (id != 0) {
//            var customerDTO = gson.fromJson(servletsHelper.parseJsonToString(req), CustomerDTO.class);
//            var customerCheck = customerService.update(id, customerDTO);
//            var jsonString = this.gson.toJson(customerCheck);
//            servletsHelper.printJson(jsonString, resp);
//        }
//    }
//
//    /**
//     * Удаление товара по id переданного в запросе
//     */
//    @RequestMapping(value="/customer", method= RequestMethod.DELETE)
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
//        var id = servletsHelper.validateAndGetIdFromRequest(req);
//        if (id != 0) {
//            customerService.deleteById(id);
//        }
//    }

//    @RequestMapping("/")
//    public ModelAndView home() {
//        List<Customer> listCustomer = customerService.listAll();
//        ModelAndView mav = new ModelAndView("index");
//        mav.addObject("listCustomer", listCustomer);
//        return mav;
//    }

//    /**
//     //     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
//     //     */
//    @RequestMapping(value="/customer", method= RequestMethod.GET)
//    protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if (req.getParameterNames().hasMoreElements()) {
//            var id = servletsHelper.getIdFromRequest(req);
//            if (id != 0) {
//                CustomerDTO customerCheck = customerService.getCustomer(id);
//                var jsonString = this.gson.toJson(customerCheck);
//                servletsHelper.printJson(jsonString, resp);
//            }
//        } else {
//            var jsonString = gson.toJson(customerService.getAllCustomer(), List.class);
//            servletsHelper.printJson(jsonString, resp);
//        }
//    }

//        /**
//     //     * Получение товара по id переданного в запросе или получение всех товаров в случае отсутсвия id
//     //     */
//    @RequestMapping(value="/customer/{id}", method= RequestMethod.GET)
//    public String get(@PathVariable("id") int id)  {
//      //  if (req.getParameterNames().hasMoreElements()) {
//        //    var id = servletsHelper.getIdFromRequest(req);
//            if (id != 0) {
//                CustomerDTO customerCheck = customerService.getCustomer(id);
//                var jsonString = this.gson.toJson(customerCheck);
//            return  jsonString;
//        } else {
//            var jsonString = gson.toJson(customerService.getAllCustomer(), List.class);
//       return jsonString;
//        }
//    }


//    @GetMapping("/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public CustomerDTO get(@RequestParam("id") int id) throws IOException {
//        return customerService.getCustomer(id);
//    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<CustomerDTO> get() throws IOException {
//        return customerService.getAllCustomer();
//    }


//    @RequestMapping(value="/customer{id}", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
//    public CustomerDTO get(@RequestParam("id") int id){
//        return customerService.getCustomer(id);
//    }


//   public CustomerDTO get(@PathVariable("id") int id) throws IOException

//   public String showOrderDetails(@RequestParam("id") String orderId, Model model){
//    protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//     if (req.getParameterNames().hasMoreElements()) {
//    var id = servletsHelper.getIdFromRequest(req);
//        if (id != 0) {
//  CustomerDTO customerCheck = customerService.getCustomer(id);
// return this.gson.toJson(customerCheck);

// servletsHelper.printJson(jsonString, resp);
//     }
//   }


//    @RequestMapping(value="/customer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<CustomerDTO> get() throws IOException {
//        //    if (req.getParameterNames().hasMoreElements()) {
//       // return gson.toJson(customerService.getAllCustomer(), List.class);
//        return customerService.getAllCustomer();
//        //     }
//    }

//    @RequestMapping(value="/customer/{id}", method= RequestMethod.GET)
//    protected void get(@PathVariable("id") int id, HttpServletResponse resp) throws IOException {
//    //    protected void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//   //     if (req.getParameterNames().hasMoreElements()) {
//         //    var id = servletsHelper.getIdFromRequest(req);
//    //        if (id != 0) {
//                CustomerDTO customerCheck = customerService.getCustomer(id);
//                var jsonString = this.gson.toJson(customerCheck);
//                servletsHelper.printJson(jsonString, resp);
//       //     }
//     //   }
//    }
//
//    @RequestMapping(value="/customer", method= RequestMethod.GET)
//    protected void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//    //    if (req.getParameterNames().hasMoreElements()) {
//            var jsonString = gson.toJson(customerService.getAllCustomer(), List.class);
//            servletsHelper.printJson(jsonString, resp);
//   //     }
//    }

//    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String getCustomer(@PathVariable("id") int id) {
//        var json = customerService.getCustomerJson(id);
//        return json.toString();
//    }
//
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public String getCustomers() {
//        var jsonArray = customerService.getCustomers();
//        return jsonArray.toString();
//    }

