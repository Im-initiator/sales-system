package com.leminhtien.api.admin;

import com.leminhtien.dto.TransportDTO;
import com.leminhtien.service.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('MANAGER')")
@RestController
public class TransportAPI {

    private  final String MANAGER_API_TRANSPORT  = "/api/manager/transport";

    @Autowired
    private ITransportService transportService;

    @PostMapping(value = MANAGER_API_TRANSPORT)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody TransportDTO transportDTO){

            transportDTO = transportService.save(transportDTO);
            return ResponseEntity.ok().body(transportDTO);
    }

    @PutMapping(value = MANAGER_API_TRANSPORT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody TransportDTO transportDTO){
            transportDTO = transportService.save(transportDTO);
            return ResponseEntity.ok().body(transportDTO);

    }

    @DeleteMapping(value = MANAGER_API_TRANSPORT)
    @ResponseBody
    public ResponseEntity<?> delete(@RequestBody Long[] ids){
        transportService.delete(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Success");
    }

}
