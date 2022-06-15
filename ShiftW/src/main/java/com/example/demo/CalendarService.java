package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class CalendarService {
    //ポイント2
    @Autowired
    CalendarRepository calendarRepository;

    //ポイント3
    public List<CalendarEntity> findAll() {
        return calendarRepository.findAll();
    }
    
    public void insert(CalendarEntity calendarEntity) {
        calendarRepository.save(calendarEntity);
    }
    public void update(CalendarEntity calendarEntity) {
        calendarRepository.save(calendarEntity);
    }
    
    
    public void delete(Integer id) {
        calendarRepository.deleteById(id);
    }
    public Optional<CalendarEntity> selectById(Integer id) {
        return calendarRepository.findById(id);
    }
    
    
    
    
    
    
}
 