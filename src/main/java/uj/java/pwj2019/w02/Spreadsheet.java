package uj.java.pwj2019.w02;

public class Spreadsheet {
    private String[][] input;

    private String setValue(String expr){
        if(expr.charAt(0)=='=')
            expr=getFormulaValue(expr);
        else if(expr.charAt(0)=='$')
            expr=getReferenceValue(expr);
        return expr;
    }

    private String getReferenceValue(String reference){
        return setValue(input[Integer.parseInt(reference.substring(2))-1][reference.charAt(1)-65]);
    }

    private String getFormulaValue(String formula){
        String firstParam=formula.substring(5, formula.indexOf(','));
        String secondParam=formula.substring(formula.indexOf(',')+1, formula.indexOf(')'));

        int firstValue= Integer.parseInt(setValue(firstParam));
        int secondValue= Integer.parseInt(setValue(secondParam));

        switch (formula.substring(1,4)){
            case "ADD":
                return String.valueOf(firstValue+secondValue);
            case "SUB":
                return String.valueOf(firstValue-secondValue);
            case "MUL":
                return String.valueOf(firstValue*secondValue);
            case "DIV":
                return String.valueOf(firstValue/secondValue);
            case "MOD":
                return String.valueOf(firstValue%secondValue);
        }

        return null;
    }

    public String[][] calculate(String[][] input) {
        this.input=input;
        for (int row = 0; row < input.length; row++)
            for (int ceil = 0; ceil < input[row].length; ceil++)
                input[row][ceil]=setValue(input[row][ceil]);
        return input;
    }

    /*public static void main(String[] args) {
        String input[][]={{"1", "2", "3"}, {"4", "5", "6"}, {"$A1", "$C1", "$B3"}, {"=ADD(10,$A1)", "=SUB($C3,$A1)", "0"}};
        //String test="test123";
        //System.out.println(test.substring(1,3));
        var spreadsheet=new Spreadsheet();
        input=spreadsheet.calculate(input);
        for(var row: input){
            for(var ceil: row)
                System.out.print(ceil+"; ");
            System.out.println();
        }
    }*/
}
