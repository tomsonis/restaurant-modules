# CQRS

Moduł udostępnia proste interfejsy pozwalające realizację wzorca CQRS.


## Konfiguracja 
W pierwszej kolejności należy wykonać implementację interfejsu:
    
   * HandlerInitializer
    
Interdejs służy do skanowania klas implementujących interfejsy:
`Command` oraz `Query`
    
Następnie należy zdefiniować następujace Beany

   * CommandExecutor
   * QueryExecutor
   * HandlerInitializer

`CommandExecutor` oraz `QueryExecutor` domyśle implementacje mają w 
moduel cqrs-application.

##Użycie
Definiowanie przykładowego commanda należy:

   * Implementacja interfejsu `Command`
   
```java
class SampleCommand implements Command<ReturnType> {
}
``` 
    
   * Implementacja `CommandHandler`
   
```java
class SampleCommandHandler implements CommandHandler<SampleCommand, ReturnType> {
   
    @Override
    public ReturnType handle(SampleCommand sampleCommand) {
        return ReturnType.empty();
    }
}
```

Aby wykonać dany command należy wstrzyknąć `CommandExecutor`:

```java
class SampleService {
    
    @Inject
    private CommandExecutor commandExecutor;
    
    public ReturnType doSomethig() {
        //Specified logic
        SampleCommand sampleCommand = new SampleCommand();
        return commandExecutor.execute(sampleCommand);
    }
}
```

Analogicznie wygląda użycie `Query`.