/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author it3530114
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "dropdownView")
@ViewScoped
public class DropdownView implements Serializable {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    private ArrayList<String> states;

    @PostConstruct
    public void init() {

        states = new ArrayList<String>();

        states.add("Alabama");
        states.add("Alaska");
        states.add("Arizona");
        states.add("Arkansas");
        states.add("California");
        states.add("Colorado");
        states.add("Connecticut");
        states.add("Delaware");
        states.add("Florida");
        states.add("Georgia");
        states.add("Hawaii");
        states.add("Idaho");
        states.add("Illinois");
        states.add("Indiana");
        states.add("Iowa");
        states.add("Kansas");
        states.add("Kentucky");
        states.add("Louisiana");
        states.add("Maine");
        states.add("Maryland");
        states.add("Massachusetts");
        states.add("Michigan");
        states.add("Minnesota");
        states.add("Mississippi");
        states.add("Missouri");
        states.add("Montana");
        states.add("Nebraska");
        states.add("Nevada");
        states.add("New Hampshire");
        states.add("New Jersey");
        states.add("New Mexico");
        states.add("New York");
        states.add("North Carolina");
        states.add("North Dakota");
        states.add("Ohio");
        states.add("Oklahoma");
        states.add("Oregon");
        states.add("Pennsylvania");
        states.add("Rhode Island");
        states.add("South Carolina");
        states.add("South Dakota");
        states.add("Tennessee");
        states.add("Texas");
        states.add("Utah");
        states.add("Vermont");
        states.add("Virginia");
        states.add("Washington");
        states.add("West Virginia");
        states.add("Wisconsin");
        states.add("Wyoming");
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

}
