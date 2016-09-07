package MathLabIntegrator;

public class MathLabFunctions {
    private double[] xArray;			//initial data array
    private double[] yArray;			// second array
    private int numberOfData;          //number of data
    
    public MathLabFunctions(double[] pXarray, double[] pYarray) {
        this.xArray=pXarray;
        this.yArray=pYarray;
        numberOfData=pXarray.length; //número de datos
    }
    
    public double correlation(){
    	//average
        double sum = 0;
        for(int actualElement = 0; actualElement < numberOfData; actualElement++){
            sum += xArray[actualElement];
        }
        double averageXarray = sum/numberOfData;

        sum=0;
        for(int actualElement = 0; actualElement < numberOfData; actualElement++){
            sum += yArray[actualElement];
        }
        double averageYarray = sum/numberOfData;
        
        //correlation coefficient
        double pxy, sx2, sy2;
        pxy = sx2 = sy2 = 0;
        for(int actualElement = 0; actualElement < numberOfData; actualElement++){
            pxy += (xArray[actualElement] - averageXarray) * (yArray[actualElement] - averageYarray);
            sx2 += (xArray[actualElement] - averageXarray)*(xArray[actualElement] - averageXarray);
            sy2 += (yArray[actualElement] - averageYarray) * (yArray[actualElement] - averageYarray);
        }
        return pxy/Math.sqrt(sx2*sy2);
    }
}
