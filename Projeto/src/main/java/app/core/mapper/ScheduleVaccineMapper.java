package app.core.mapper;

import app.core.domain.model.*;
import app.core.dto.ScheduleVaccineDTO;

import java.util.ArrayList;
import java.util.List;

public class ScheduleVaccineMapper {

    public static List<ScheduleVaccineDTO> toListDTO(List<ScheduleVaccine> listSV) {
        List<ScheduleVaccineDTO> listSVDto = new ArrayList<>();

        for (int i = 0; i < listSV.size(); i++) {
            ScheduleVaccine sv = listSV.get(i);

            Date date = sv.getDate();
            Time time = sv.getTime();

            ScheduleVaccineDTO svDto = new ScheduleVaccineDTO(date, time);

            listSVDto.add(svDto);
        }

        return listSVDto;
    }

    public static ScheduleVaccine toModel(ScheduleVaccineDTO svDto) {
        SNSUser snsUser = svDto.getSNSUser();
        Date date = svDto.getDate();
        Time time = svDto.getTime();
        VaccinationCenter vaccinationCenter = svDto.getVaccinationCenter();
        VaccineType vaccineType = svDto.getVaccineType();

        return new ScheduleVaccine(snsUser, vaccinationCenter, date, time, vaccineType);

    }
}
