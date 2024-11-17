package org.firstinspires.ftc.teamcode.initialize;

public class CustomMethods {

    public CustomMethods(){

    }

// used to change range of values like the arduino map method, Verry usefull for direct controller input :) poopy pants
    public static double mapVal(double var, double minInputVal, double maxInputVal, double minOutVal, double maxOutVal){

        // big math thing i had a stroke putting togeather if you delete my hard work i will elemanate you from this mortal plane
        return (maxOutVal-minOutVal)*(var - minInputVal)/(maxInputVal-minInputVal)+minOutVal;
    // counter for number of times you got the peramaters mixed up: 1  (change everytime you mix it up)
    }

}
