package Info_Display.V20.lib.Exception;

import Info_Display.V20.lib.Exception.CalenderException.CalenderException;
import Info_Display.V20.lib.Exception.RoomStatusExceptions.RoomStatusException;
import Info_Display.V20.lib.Exception.SpaceInformationException.SpaceInformationException;
import Info_Display.V20.lib.Exception.StuffInSpaceExceptions.StuffInSpaceException;
import Info_Display.V20.persistence.repositroy.SpaceInformationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(StuffInSpaceException.class)
    public ResponseEntity<String> handler(StuffInSpaceException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomStatusException.class)
    public ResponseEntity<String> handler(RoomStatusException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoAccessForipAddressException.class)
    public ResponseEntity<String> handler(NoAccessForipAddressException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CalenderException.class)
    public ResponseEntity<String> handler(CalenderException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SpaceInformationException.class)
    public ResponseEntity<String> handler(SpaceInformationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
