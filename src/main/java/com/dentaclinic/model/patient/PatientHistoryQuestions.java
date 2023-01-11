package com.dentaclinic.model.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientHistoryQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionsAboutSuffering; //txt Editor
    private String moreInfoAboutServices; //txt Editor
    // للاطمئنان على صحتك وانتظام سير العلاج ... الراجاء الاجابه ع الاسئله التاليه
    // هل تعانى فى الوقت الحاضر من مشاكل فى :
    private String moreQuestionsAboutSuffering; //txt Editor
    //Female Questions ... add condition if gender is female
    private String femaleQuestions; //txtEditor
}
