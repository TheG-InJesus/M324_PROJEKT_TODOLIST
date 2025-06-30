GitHUB Actions:

Im Ordner mit der Name 'workflows' im '.github' Ordner ein .yaml Datei anlegen
Nach jedem push oder pull wird der Action ausgelöst:


![Alt text](./screenshots/maven-yaml.png)


Dann als job den Build anlegen mit den Testsd:


![Alt text](./screenshots/Maven-build.png)


Nach dem push (oder pull) kann man auf GitHub unter 'Actions' sehen wie die Test gelaufen sind:


![Alt text](./screenshots/Workflow.png)

