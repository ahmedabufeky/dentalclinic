package com.dentaclinic.controller;

import com.dentaclinic.model.patient.PatientHistory;
import com.dentaclinic.model.patient.PatientHistoryQuestions;
import com.dentaclinic.repository.patient.PatientHistoryQuestionsRepository;
import com.dentaclinic.repository.patient.PatientHistoryRepository;
import com.dentaclinic.services.patient.PatientHistoryQuestionsServices;
import com.dentaclinic.services.patient.PatientHistoryServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class PatientHistoryController {
    private final PatientHistoryServices patientHistoryServices;
    private final PatientHistoryRepository patientHistoryRepository;
    private final PatientHistoryQuestionsRepository patientHistoryQuestionsRepository;
    private final PatientHistoryQuestionsServices patientHistoryQuestionsServices;

    @RequestMapping(value = "/addPatientHistory", method = RequestMethod.GET)
    public String addPatientHistory(Model model){
        PatientHistory patientHistory = new PatientHistory();
        model.addAttribute("patientHistory", patientHistory);
        return "patientHistory/addPatientHistory";
    }

    @RequestMapping(value = "/addPatientHistory", method = RequestMethod.POST)
    public String addPatientHistoryPost(@ModelAttribute(name = "patientHistory") PatientHistory patientHistory,
                                 RedirectAttributes redirectAttributes) throws IOException {

        patientHistoryRepository.save(patientHistory);
        redirectAttributes.addFlashAttribute("message", "Patient History has been saved successfully.");

        return "redirect:/patientHistoryList";
    }

    @RequestMapping("/patientHistoryDetails")
    public String productDetails(@RequestParam("id") Long id, Model model) throws Exception {

        Optional<PatientHistory> patientHistory = patientHistoryServices.findOne(id);
        if(patientHistory.isPresent()) {
            System.out.println(patientHistory.get().getId());
            model.addAttribute("patientHistory", patientHistory.get());
            return "patientHistory/patientHistoryDetails";
        } else {
            throw new Exception("Patient History not found");
        }
    }

    @RequestMapping("/updatePatientHistory")
    public String updatePatientHistory(Model model,@RequestParam("id") Long id) throws Exception {

        Optional<PatientHistory> patientHistory = patientHistoryServices.findOne(id);
        if(patientHistory.isPresent()) {
            System.out.println(patientHistory.get().getId());
            model.addAttribute("patientHistory", patientHistory.get());
            return "patientHistory/updatePatientHistory";
        } else {
            throw new Exception("Patient History not found");
        }
    }

    @RequestMapping(value = "/updatePatientHistory", method = RequestMethod.POST)
    public String updatePatientHistoryPost(@ModelAttribute("patientHistory") PatientHistory patientHistory,
                                           RedirectAttributes redirectAttributes) throws IOException{

        patientHistoryRepository.save(patientHistory);

        redirectAttributes.addFlashAttribute("message", "Patient History has been updated successfully.");

        return "redirect:/patientHistoryList";
    }

    @RequestMapping("/patientHistoryList")
    public String patientHistory(Model model){
        List<PatientHistory> patientHistoryList = patientHistoryServices.findAll();
        model.addAttribute("patientHistoryList", patientHistoryList);
        return "patientHistory/patientHistoryList";
    }

    @RequestMapping(value = "/removePatientHistory/{id}", method = RequestMethod.GET)
    public String removePatientHistory(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        patientHistoryServices.removePatientHistory(id);
        redirectAttributes.addFlashAttribute("message", "Patient History has been Deleted Successfully.");
        return "redirect:/patientHistoryList";
    }
}
