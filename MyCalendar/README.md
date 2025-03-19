D'abord, j'ai fait un refactoring global du projet :
    - Polymorphisme et suppression des conditionnelles
    - Utilisation exclusive des Value Objects, pas de primitives nues
Ensuite, j'ai implémenté toutes les nouvelles fonctionnalités qui marchent correctement :
    - Ajout d'un noveau type d'évnement -> Jour Important (évènement sur une journée)
    - Obtenir la liste des événements pour une période donnée.
    - Détecter automatiquement les conflits entre événements (chevauchement horaire). 
    - Générer une description spécifique à chaque type d'événement.
    - Pouvoir supprimer un événement par son identifiant métier (ajouter un EventId) -> Dans le Menu Création d'Événements.

J'ai essayé d'ajouter Maven au projet pour effectuer des tests, mais je n'ai pas réussi. 
Je n'ai pas réussi non plus à importer Junit pour les tests.
Je n'ai donc pas fait de test pour tester les nouvelles fonctionnalités ajoutées.