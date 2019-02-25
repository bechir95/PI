/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lynden.gmaps.directions;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback{
    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
     protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO1
         mapView.addMapInializedListener(this);
        to.bindBidirectional(toTextField.textProperty());
        from.bindBidirectional(fromTextField.textProperty());
    }    

    @FXML
    private void toTextFieldAction(ActionEvent event) {
      /*  DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        DirectionsPane directionsPane = null;
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));*/
    

    }

    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
     DirectionsService directionsService = new DirectionsService();
     DirectionsPane directionsPane = mapView.getDirec();
        options.center(new LatLong(47.606189, -122.335842))
                .zoomControl(true)
                .zoom(12)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
       // DirectionsService directionsService = new DirectionsService();
       // DirectionsPane directionsPane = mapView.getDirec();
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
