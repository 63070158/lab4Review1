package com.example.lab4review;

import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "/index1")
public class MathView extends VerticalLayout {
    public MathView() {
        TextField Num1 = new TextField();
        Num1.setLabel("Number1");
        TextField Num2 = new TextField();
        Num2.setLabel("Number2");
        TextField answer = new TextField();
        answer.setLabel("Answer");
        Span opTxt = new Span("Operator");
        Button plus = new Button("+");
        Button minus = new Button("-");
        Button multiply = new Button("x");
        Button divide = new Button("/");
        Button mod = new Button("Mod");
        Button max = new Button("Max");

        HorizontalLayout buttonGroup = new HorizontalLayout();
        buttonGroup.add(plus, minus, multiply, divide, mod, max);
        this.add(Num1, Num2, opTxt, buttonGroup, answer);

        plus.addClickListener(event ->  {
            double n1 = Double.parseDouble(Num1.getValue());
            double n2= Double.parseDouble(Num2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/add"+n1+"/"+n2).retrieve().bodyToMono(String.class).block();
            answer.setValue(out);
        });
    }
}
