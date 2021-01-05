/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DecimalFormat;


/**
 *
 * @author unzalu.jon
 */
public final class Score {
    private String type;
    private double score;

    public Score() {
    }

    public Score(String type, double score) {
        this.type = type;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###.##");
        String formatted = df.format(score); 
        if(formatted.length()==3){
            formatted = formatted + "  ";
        }
        else if(formatted.length()==4){
            formatted = formatted + " ";
        }
        else if(formatted.length()==2){
            formatted = formatted + "   ";
        }
        return "[Type: " + type.toUpperCase() + ", Score:" + formatted + "]";
    }
       
}
