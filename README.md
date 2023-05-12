Aprovação de cartão de crédito

Este projeto tem como objetivo aprovar ou não a solicitação de um cartão de crédito para um cliente, baseado em sua renda mensal.
Tecnologias utilizadas

    Java 17
    Spring Boot
    RabbitMQ
    Docker

O projeto também foi desenvolvido seguindo o conceito de modularização, com o objetivo de facilitar a manutenção e o desenvolvimento de novas funcionalidades.
Como funciona

O sistema recebe a solicitação de um cartão de crédito para um cliente. Com base na renda mensal do cliente, o sistema avalia se a solicitação deve ser aprovada ou não.

Caso a solicitação seja aprovada, é gerado um evento que é enviado para uma fila do RabbitMQ. Um outro serviço interno irá então processar esse evento e, caso tudo esteja correto, aprovar a criação do cartão de crédito para o cliente com o limite baseado depois de aprovado na sua idade. 
