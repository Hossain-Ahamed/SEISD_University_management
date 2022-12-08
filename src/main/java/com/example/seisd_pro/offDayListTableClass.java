package com.example.seisd_pro;

import java.time.LocalDate;

public class offDayListTableClass {
    private LocalDate OffDay;

    public offDayListTableClass(LocalDate offDay) {
        OffDay = offDay;
    }

    public LocalDate getOffDay() {
        return OffDay;
    }

    public void setOffDay(LocalDate offDay) {
        OffDay = offDay;
    }
}
