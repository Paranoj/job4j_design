package ru.job4j.ood.isp.example;

public interface SmartPhone {
    void initConnection();

    void GPS();

    void IrDA();

    /* Нарушение ISP. В данном контексте ИК-порт является устаревшим стандартом передачи данных,
    * но т.к. он объявлен в интерфейсе, его придется заглушить, хоть он и не используется на современных устройствах.*/
}
