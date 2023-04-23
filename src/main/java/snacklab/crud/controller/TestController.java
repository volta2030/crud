package snacklab.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    private DriverManagerDataSource dataSource;

    private List<Country> getCountries() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM world.country LIMIT 20";
        List<Country> countries =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));
        return countries;
    }

    private void insert(String sql){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql);
    }

    private void insert(String sql, Object[] params){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, params);
    }


    @GetMapping("/test")
    public String index(Model model) {
        model.addAttribute("name", "Load successful!");
        insert("INSERT INTO country (Code) VALUE('ZZZ')");
        getCountries();
        return "index";
    }
}