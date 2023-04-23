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
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public String test(Model model) {

        List<Country> contries = getCountries();
        Country country = contries.get(0);
        setCountryToModel(country, model);

        return "test";
    }

    private void setCountryToModel(Country country, Model model){
        model.addAttribute("code", country.getCode());
        model.addAttribute("continent", country.getContinent());
        model.addAttribute("region", country.getRegion());
        model.addAttribute("surfaceArea", country.getSurfaceArea());
        model.addAttribute("indepYear", country.getIndepYear());
        model.addAttribute("population", country.getPopulation());
        model.addAttribute("lifeExpectancy", country.getLifeExpectancy());
        model.addAttribute("gnp", country.getGnp());
        model.addAttribute("gnpOld", country.getGnpOld());
        model.addAttribute("localName", country.getLocalName());
        model.addAttribute("governmentForm", country.getGovernmentForm());
        model.addAttribute("headOfState", country.getHeadOfState());
        model.addAttribute("capital", country.getCapital());
        model.addAttribute("code2", country.getCode2());

    }

    private List<Country> getCountries() {
        String sql = "SELECT * FROM world.country LIMIT 20";
        List<Country> countries =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Country.class));
        return countries;
    }

    private void insert(String sql){
        jdbcTemplate.update(sql);
    }

    private void insert(String sql, Object[] params){
        jdbcTemplate.update(sql, params);
    }
}