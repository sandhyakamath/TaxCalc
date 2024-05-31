FUNCTION STRING CMD_GET_S(INTEGER OFFSET, STRING s )
     return s; 
END
FUNCTION INTEGER CMD_GET_I(INTEGER OFFSET,STRING s )
     return 1; 
END
FUNCTION NUMERIC CMD_GET_D(INTEGER OFFSET, STRING s )
     return 0.0; 
END
FUNCTION BOOLEAN CMD_GET_B(INTEGER OFFSET,STRING s )
     return TRUE; 
END
FUNCTION STRING PREDICT(STRING ARGS)

NUMERIC Var_9;
NUMERIC Var_8;
NUMERIC Var_1;
NUMERIC Var_0;
NUMERIC Var_3;
NUMERIC Var_12;
NUMERIC Var_4;
NUMERIC Var_7;
NUMERIC Var_6;
Var_9 = CMD_GET_D(9,ARGS);
Var_8 = CMD_GET_D(8,ARGS);
Var_1 = CMD_GET_D(1,ARGS);
Var_0 = CMD_GET_D(0,ARGS);
Var_3 = CMD_GET_D(3,ARGS);
Var_12 = CMD_GET_D(12,ARGS);
Var_4 = CMD_GET_D(4,ARGS);
Var_7 = CMD_GET_D(7,ARGS);
Var_6 = CMD_GET_D(6,ARGS);
IF ( Var_6 >= 6.5) THEN
  return "Y";
ELSE  

  IF ( Var_6 >= 5.7) THEN
    IF ( Var_3 >= 51.0) THEN
      IF ( Var_8 >= 2.9) THEN
        IF ( Var_0 >= 363.0) THEN
          return "Y";
        ELSE  

          return "P";
        ENDIF  
      ELSE  

        return "Y";
      ENDIF  
    ELSE  

      return "P";
    ENDIF  
  ELSE  

    IF ( Var_12 >= 25.0) THEN
      return "Y";
    ELSE  

      IF ( Var_7 >= 5.0) THEN
        IF ( Var_8 >= 2.0) THEN
          return "Y";
        ELSE  

          IF ( Var_4 >= 7.7) THEN
            return "Y";
          ELSE  

            IF ( Var_9 >= 1.6) THEN
              IF ( Var_1 >= 45383.0) THEN
                return "N";
              ELSE  

                return "Y";
              ENDIF  
            ELSE  

              return "N";
            ENDIF  
          ENDIF  
        ENDIF  
      ELSE  

        return "N";
      ENDIF  
    ENDIF  
  ENDIF  
ENDIF  
END