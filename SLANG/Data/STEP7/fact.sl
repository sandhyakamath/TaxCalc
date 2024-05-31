FUNCTION Integer FACT( Integer d )
    IF ( d <= 0 ) THEN
          return 1;
    ELSE
          return d*FACT(d-1);
    ENDIF

END

////////////////////
//
//
//  Entry Point
//
//
FUNCTION BOOLEAN MAIN()
Integer d;
d=0;
While ( d <= 10 )
    PRINTLINE FACT(d);
    d = d+1;
Wend
END