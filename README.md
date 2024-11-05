# Projet_de_gestion_du_publication_scientifique

Ce projet est d’une application pour la gestion de la recherche scientifique, visant à simplifier le partage et la validation des travaux de recherche.

## Fonctionnalités :

# Gestion des soumissions d'articles :
- Les auteurs peuvent soumettre des articles, avec un correspondant désigné pour gérer la soumission.
- Chaque article inclut des informations telles que le titre, les auteurs, l'institution d'affiliation, le résumé, les mots clés, la taille (en nombre de mots), et le contenu.
- Le correspondant peut modifier les détails de l'article ou annuler la soumission tant qu'elle est en cours.

# Suivi de l'état de soumission :
- Lorsqu'un article est en cours de soumission, il peut être mis à jour ou modifié.
- Une fois la soumission validée, le système enregistre la date de soumission, et l'article ne peut plus être supprimé par le correspondant.
  
# Attribution des évaluateurs :
- L'éditeur assigne trois évaluateurs pour les articles courts et quatre pour les articles longs.
- Les restrictions d'attribution empêchent les auteurs, l'éditeur ou les membres de la même institution que les auteurs d'évaluer l'article.
  
# Suivi des décisions des évaluateurs :
- Chaque évaluateur peut émettre une décision parmi plusieurs options : acceptation, révision mineure, révision majeure, ou refus.
- Le système enregistre chaque décision, et les avis influencent la décision finale de l'éditeur.
  
# Révision de l'article :
- En cas de révision mineure ou majeure, les auteurs peuvent soumettre une nouvelle version de l'article.
- Le système enregistre la date de chaque révision et transmet le nouveau contenu aux évaluateurs.
  
# Décision finale et publication :
- Les décisions définitives sont soit "acceptation" soit "refus".
- Les articles acceptés sont publiés et conservés dans le système, tandis que les articles refusés sont supprimés.
- 
# Gestion des rôles utilisateurs :
- Différents rôles d’utilisateur : auteur, éditeur, évaluateur, chacun avec des permissions spécifiques pour garantir la sécurité et l'intégrité des données.

## Technologies Utilisées
- **Angular pour le frontend**
- **Spring Boot pour backend** 
- **MySql comme base de donnes**
- **Angular Materiel**
- **Spring Security ,Data,MVC,IOC,ID ...**

## Auteur
Développé par Oussama Et-taghy - [LinkedIn](https://www.linkedin.com/in/oussama-et-taghy-a61166266/)
