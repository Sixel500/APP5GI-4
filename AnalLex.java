package app6;

/** @author Ahmed Khoumsi */

import static java.lang.Integer.max;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

private String input;
private int ptr;

	
/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex( String in) {  // arguments possibles
    this.input = in;
    this.ptr = 0;
  }

public boolean resteTerminal(){
    while(ptr < input.length() && Character.isWhitespace(input.charAt(ptr))){
      ptr++;
    }
    return ptr < input.length();
}
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  public Terminal prochainTerminal( ) {
    int etat = 0;
    String lexeme = "";

     if (!resteTerminal()) {
       return new Terminal(Terminal.Type.EOF, "");
     }

     while (ptr<input.length()){
       char car = input.charAt(ptr);

       switch (etat) {
         case 0:
           if (car == '+'){
             ptr++;
             return new Terminal(Terminal.Type.PLUS, "+");
           }
           if (car == '-'){
             ptr++;
             return new Terminal(Terminal.Type.MOINS, "-");
           }
           if (car == '/'){
             ptr++;
             return new Terminal(Terminal.Type.DIVISE, "/");
           }
           if (car == '*'){
             ptr++;
             return new Terminal(Terminal.Type.FOIS, "*");
           }
           if (car == '('){
             ptr++;
             return new Terminal(Terminal.Type.PARENTHESE, "(");
           }
           if (car == ')'){
             ptr++;
             return new Terminal(Terminal.Type.FINPARENTHESE, ")");
           }
           else if (Character.isDigit(car)){
             ptr++;
             lexeme += car;
             etat = 1;
           }
           else if (Character.isUpperCase(car)){
             ptr++;
             lexeme += car;
             etat = 2;
         } else if (car == '_'){
               ptr++;
               lexeme += car;
               etat = 3;
           }
           else {
             ErreurLex("Charactère non reconnu :" + car);
             ptr++;
             return null;
           }
           break;

           case 1:
             if (Character.isDigit(car)){
               lexeme += car;
               ptr++;
             }
             else {
               return new Terminal(Terminal.Type.NOMBRE, lexeme);
             }
             break;

         case 2:
           if (Character.isLetter(car)){
             lexeme += car;
             ptr++;
           }
           else if (car == '_'){
             ptr++;
             lexeme += car;
             etat = 3;
           }
           else {
             return new Terminal(Terminal.Type.OPERANDE, lexeme);
           }
           break;
           case 3:
               if (car == '_') {
                   ErreurLex("Double tiret bas interdit : " + this.getLastChars());
               }else if (Character.isLetter(car)){
                   lexeme += car;
                   ptr++;
                   etat = 2;
               }
               break;

       }

     }


     if (etat == 1) {
       return new Terminal(Terminal.Type.NOMBRE, lexeme);
     }
     if (etat == 2){
       return new Terminal(Terminal.Type.OPERANDE, lexeme);
     }
     if (etat == 3){
         ErreurLex("Operande finit par un tiret bas: " + this.getLastChars() );
     }
     return null;
  }

 public String getLastChars(){
     return input.substring(max(0,ptr-10),ptr+1);
 }

/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {	
    System.err.println("Erreur Lexicale : " + s);
    System.exit(1);
  }

  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);

    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t = null;
    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();
      System.out.println(String.valueOf(lexical.ptr) + ". " + t.type + " : " + t.chaine + "\n");  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
