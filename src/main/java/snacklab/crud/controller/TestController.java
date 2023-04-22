package snacklab.crud.controller;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import snacklab.crud.Country;

import java.util.List;

@Controller
public class TestController {
    public List<Country> getCountries() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/world?serverTimezone=UTC&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("backend1!");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM world.country LIMIT 20";
        List<Country> countries =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));

        return countries;

    }

    @GetMapping("/test")
    public String index(Model model) {
        model.addAttribute("name", "Load successful!");
        getCountries();
        return "index";
    }
}