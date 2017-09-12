package com.digdes.school.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * ...
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
@SuppressWarnings("UnusedDeclaration")
@Entity
@Data
@NoArgsConstructor
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private City city;

    private Long cityId;

    private Date dateReceived;

    private Date date;

    //Облачность {0 - ясно, 1- малооблачно, 2 - облачно, 3 - пасмурно}
    private Integer cloudiness;
    //тип осадков: 4 - дождь, 5 - ливень, 6,7 – снег, 8 - гроза, 9 - нет данных, 10 - без осадков
    private Integer precipitation;
    //интенсивность осадков, если они есть. 0 - возможен дождь/снег, 1 - дождь/снег
    private Integer rPower;
    //вероятность грозы, если прогнозируется: 0 - возможна гроза, 1 - гроза
    private Integer sPower;

    //атмосферное давление, в мм.рт.ст.
    private Integer pressureMin;

    private Integer pressureMax;

    //Температура
    private Integer temperatureMin;

    private Integer temperatureMax;
    //Приземный ветер
    // направление ветра в румбах, 0 - северный, 1 - северо-восточный,  и т.д.
    private Integer windDirection;
    //минимальное и максимальное значения средней скорости ветра, без порывов
    private Integer windMin;

    private Integer windMax;
    //относительная влажность воздуха, в %
    private Integer relWetMin;

    private Integer relWetMax;
    //	    комфорт - температура воздуха по ощущению одетого по сезону человека, выходящего на улицу
    private Integer heatMin;

    private Integer heatMax;
}
