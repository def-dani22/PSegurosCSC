
package servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para restar complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="restar"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numa" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="numb" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "restar", propOrder = {
    "numa",
    "numb"
})
public class Restar {

    protected double numa;
    protected double numb;

    /**
     * Obtiene el valor de la propiedad numa.
     * 
     */
    public double getNuma() {
        return numa;
    }

    /**
     * Define el valor de la propiedad numa.
     * 
     */
    public void setNuma(double value) {
        this.numa = value;
    }

    /**
     * Obtiene el valor de la propiedad numb.
     * 
     */
    public double getNumb() {
        return numb;
    }

    /**
     * Define el valor de la propiedad numb.
     * 
     */
    public void setNumb(double value) {
        this.numb = value;
    }

}
