
package servicios;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CalcuMate", targetNamespace = "http://Servicios/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CalcuMate {


    /**
     * 
     * @param numa
     * @param numb
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "divide", targetNamespace = "http://Servicios/", className = "servicios.Divide")
    @ResponseWrapper(localName = "divideResponse", targetNamespace = "http://Servicios/", className = "servicios.DivideResponse")
    @Action(input = "http://Servicios/CalcuMate/divideRequest", output = "http://Servicios/CalcuMate/divideResponse")
    public double divide(
        @WebParam(name = "numa", targetNamespace = "")
        double numa,
        @WebParam(name = "numb", targetNamespace = "")
        double numb);

    /**
     * 
     * @param numa
     * @param numb
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "restar", targetNamespace = "http://Servicios/", className = "servicios.Restar")
    @ResponseWrapper(localName = "restarResponse", targetNamespace = "http://Servicios/", className = "servicios.RestarResponse")
    @Action(input = "http://Servicios/CalcuMate/restarRequest", output = "http://Servicios/CalcuMate/restarResponse")
    public double restar(
        @WebParam(name = "numa", targetNamespace = "")
        double numa,
        @WebParam(name = "numb", targetNamespace = "")
        double numb);

    /**
     * 
     * @param numa
     * @param numb
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "multiplicar", targetNamespace = "http://Servicios/", className = "servicios.Multiplicar")
    @ResponseWrapper(localName = "multiplicarResponse", targetNamespace = "http://Servicios/", className = "servicios.MultiplicarResponse")
    @Action(input = "http://Servicios/CalcuMate/multiplicarRequest", output = "http://Servicios/CalcuMate/multiplicarResponse")
    public double multiplicar(
        @WebParam(name = "numa", targetNamespace = "")
        double numa,
        @WebParam(name = "numb", targetNamespace = "")
        double numb);

    /**
     * 
     * @param name
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hello", targetNamespace = "http://Servicios/", className = "servicios.Hello")
    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://Servicios/", className = "servicios.HelloResponse")
    @Action(input = "http://Servicios/CalcuMate/helloRequest", output = "http://Servicios/CalcuMate/helloResponse")
    public String hello(
        @WebParam(name = "name", targetNamespace = "")
        String name);

    /**
     * 
     * @param numa
     * @param numb
     * @return
     *     returns double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sumar", targetNamespace = "http://Servicios/", className = "servicios.Sumar")
    @ResponseWrapper(localName = "sumarResponse", targetNamespace = "http://Servicios/", className = "servicios.SumarResponse")
    @Action(input = "http://Servicios/CalcuMate/sumarRequest", output = "http://Servicios/CalcuMate/sumarResponse")
    public double sumar(
        @WebParam(name = "numa", targetNamespace = "")
        double numa,
        @WebParam(name = "numb", targetNamespace = "")
        double numb);

}
