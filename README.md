#Restaurant modules

Projekt przedstawia aplikację wspomagająca składanie oraz realizację zamówień internetowych
dla wielu restauracji.  
 
Projekt składa się z modułów pozwalających realizację internetowej restauracji.
Występuja 4 główne moduły:
   * restaurant-core: odpowiada za domenę restauracji 
   * restaurant-orders: odpowiada za domenę zamówień
   * restaurant-products: odpowiada za domenę dań
   * restaurant-user: odpowiada za użytkowników
   
Dodatkowe są także dwa moduły:
   * common 
   * test-module
   
Każdy główny moduł został podzielony na podmoduły:
   * api
   * application
   * domain
   * infrastructure-*
   
Podmoduł `infrastrucutre` został opracowany dla Spring oraz Hibernate. 

W projekcie wykorzystany jest [CQRS](https://github.com/tomsonis/cqrs).

Aktualnie wykonywana jest refactoryzacja projektu.
