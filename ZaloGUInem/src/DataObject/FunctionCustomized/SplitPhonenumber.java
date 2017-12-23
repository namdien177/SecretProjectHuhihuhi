package DataObject.FunctionCustomized;

public class SplitPhonenumber {
    public String[] SplitPhone(String TextFromTextField){
        return TextFromTextField.split("\\r?\\n");
    }
}
