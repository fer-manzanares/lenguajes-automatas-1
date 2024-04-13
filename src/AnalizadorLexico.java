/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LENOVO
 */
public class AnalizadorLexico {
    int estado;
    char[] entrada;
    int posicion;
    
    public enum TOKEN {
        ID, FLOAT, RETURN, PARENTESIS_APER, PARENTESIS_CIERRE, ERROR, HAS_NEXT, EOF;
        public String toString(){
            switch (this) {
                case ID:
                    return "IDENTIFICADOR";
                    case FLOAT:
                    return "FLOAT";
                    case PARENTESIS_APER:
                    return "PAR_APERTURA";
                    case PARENTESIS_CIERRE:
                    return "PAR_CIERRE";
                    case RETURN:
                    return "RETURN";
                    case ERROR:
                    return "ERROR";
                    
                default:
                    return "";
            }
        }
    }
    
    public AnalizadorLexico(char[] entrada) {
        this.estado = 0;
        this.entrada = entrada;
        this.posicion = -1;
    }
    
    public TOKEN automata(){
        if (this.posicion == this.entrada.length - 1) return TOKEN.EOF;
        while (true){
        char car = this.entrada[++this.posicion];
        switch (estado) {
            case 0:
                if (car == ' ') break;
                
                if (car == '('){
                    estado = 0;
                    return TOKEN.PARENTESIS_APER;
                }else if(car == ')'){
                estado = 0;
                return TOKEN.PARENTESIS_CIERRE;
                } else if (car == 'f'){
                    estado = 1;
                }else if (car == 'r'){
                    estado = 7;
                }else{
                    estado = 14;
                }
                 return TOKEN.HAS_NEXT;
            case 1:
                if (car == 'l')
                    estado = 2;
                else if (this.isLetter(car))
                    estado = 14;
                else {
                    estado = 15;
                }
                return TOKEN.HAS_NEXT;
            case 2:
                if (car == 'o')
                    estado = 3;
                else if (this.isLetter(car))
                    estado = 14;
                else {
                    estado = 15;
                }
                return TOKEN.HAS_NEXT;
                case 3:
                if (car == 'a')
                    estado = 4;
                else if (this.isLetter(car))
                    estado = 14;
                else {
                    estado = 15;
                }
                return TOKEN.HAS_NEXT;
            case 4:
                if (car == 't')
                    estado = 5;
                else if (this.isLetter(car))
                    estado = 14;
                else {
                    estado = 15;
                }
                return TOKEN.HAS_NEXT;
            case 5:
                if (this.isLetter(car))
                    estado = 14;
                else {
                    estado = 0;
                    this.posicion--;
                    return TOKEN.FLOAT;
                
                return TOKEN.HAS_NEXT;
            case 7:
                if (car == 'e'){
                    estado = 8;
                    break;
                }else if (this.isLetter(car) || this.isDigit(car)){
                    estado = 14;
                    break;
                }
                else{
                    estado = 0;
                    this.posicion--;
                    return TOKEN.ID;
                }
            case 14:
                if (this.isLetter(car) || this.isDigit(car)){
                    estado = 14;
                    break;
                }
                else {
                this.estado = 0;
                this.posicion--;
                return TOKEN.ID;
                }
            case 15:
                
            case 16:
                return TOKEN.PARENTESIS_APER;                
            case 17:
                return TOKEN.PARENTESIS_CIERRE;                
                            
            default:
                return TOKEN.ERROR;
        }
        }
    }
    
    public void reset(){
        this.estado = 0;
    }
    
    private boolean isLetter(char car){
        return ((car >= 'a' && car <= 'z') || (car >= 'A' && car <= 'Z')) ? true : false;
    }
    
    private boolean isDigit(char car){
        return (car >= '0' && car <= '9') ? true : false;
    }
}
 