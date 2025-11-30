# CuidarMais, o seu Lembrete de Medicamentos
> Projeto de sistematização CEUB
##### Turma B - 0925
##### Professor Dr. Romes Heriberto
##### Alunos do grupo:
- Fernando Tomio Yamamotu Tanaka
- Marcos Fernando Turial de Moraes


# 1. Descrição do Problema
O envelhecimento populacional amplia desafios relacionados ao autocuidado, especialmente no que diz respeito ao uso contínuo de medicamentos. Muitos idosos e pessoas com rotinas intensas, condições crônicas ou necessidades especiais, enfrentam dificuldades para lembrar horários, dosagens e frequência de uso. Esse cenário pode gerar falhas no tratamento, agravamento de doenças e redução da qualidade de vida.

O aplicativo CuidarMais surge para mitigar esse problema por meio de uma solução intuitiva que organiza e lembra o usuário sobre os horários adequados de sua medicação, oferecendo mais segurança e autonomia.

# 2. Justificativa 
O aplicativo está alinhado ao **Objetivo de Desenvolvimento Sustentável (ODS) 3 - Saúde e Bem-Estar**, da ONU, que incentiva ações capazes de garantir vidas saudáveis e promover o bem-estar em todas as idades.

A proposta responde a uma demanda real da sociedade, apoiando a população idosa que está crescendo rapidamente e necessita de ferramentas acessíveis e humanizadas para seu cuidado diário.

A solução contribui diretamente para:
- Aumentar a adesão ao tratamento medicamentoso
- Fortalecer a autonomia da pessoa idosa
- Prevenir internações evitáveis
- Reduzir riscos causados por esquecimentos ou uso incorreto de medicamentos

# 3. Público-Alvo
O CuidarMais beneficia especialmente:
- Idosos que fazem uso frequente ou contínuo de medicamentos.
- Adultos com doenças crônicas, como hipertensão e diabetes.
- Cuidadores, familiares ou profissionais que acompanham usuários dependentes.
- Pessoas com rotina intensa, que podem esquecer compromissos de saúde.
  
O aplicativo foi projetado para ser simples, visualmente acessível e responsivo para atender tanto usuários experientes quanto iniciantes em tecnologia.

# 4. Objetivos do Aplicativo 
O CuidarMais tem como metas principais:
- Reduzir esquecimentos de doses por meio de lembretes confiáveis notificando o usuário no horário correto.
- Organizar tratamentos registrando nome do medicamento, dosagem, horário(s) e dias da semana.
- Aumentar a autonomia de idosos e pessoas em tratamento contínuo.
- Gerar rotina de cuidados com metas simples e bem definidas.
- Disponibilizar uma experiência intuitiva, com telas responsivas e fácil navegação.

# 5. Tipo de Aplicação
O CuidarMais é um aplicativo nativo Android, desenvolvido com as seguintes tecnologias:
- Kotlin como linguagem principal de desenvolvimento
- Android Jetpack como fonte de bibliotecas auxiliares
- Room Database para persistência dos dados
- Navigation Component para elaboração de rotas e navegação
- Material Design para criação de designs e interfaces
Por ser nativo, oferece melhor desempenho, acessibilidade e integração com recursos do sistema, como notificações.

# 6. Instalação e uso

### Instalação
1. Baixe ou clone o repositório: `git clone https://github.com/ftanakaceub/PDM-B-092520900`
2. Abra o projeto no Android Studio (Arctic Fox ou superior).
3. Aguarde a sincronização das dependências.
4. Execute o aplicativo utilizando:
    - Um dispositivo físico Android (API 24+), ou
    - Um emulador criado no Android Studio.

### Uso
1. Tela de Login
  - Acesse com uma conta cadastrada ou clique em Criar novo usuário.

  ![Tela Login](https://github.com/user-attachments/assets/43b3cc7c-40e7-4736-970d-b44005a4dbfb)

2. Tela de criação de usuário
  - Insira nome, email e senha.
  - Utilize o botão de voltar caso deseje retornar à tela de login.

  ![Tela Cadastro](https://github.com/user-attachments/assets/f82fa07b-7711-4c81-995f-b098214e6951)

3. Tela Principal
  - Navegue pelo menu inferior entre as telas principais.
  - O menu Home sempre mostrará o próximo medicamento a ser tomado, considerando todos os registrados.
    
  - O menu Medicamentos mostrará todos os Lembretes cadastrados, os horários e dias da semana definidos para cada um.

  ![Aba Medicamentos](https://github.com/user-attachments/assets/960e57f4-8ca1-4be2-b59f-136269ba5472)
  
  - O menu Perfil permite visualizar e alterar os dados do usuário.

  ![Aba Perfil](https://github.com/user-attachments/assets/74c0ff1b-ea1d-4136-b41b-6e43ff4be588)

4. Adicionar Lembrete de Medicamento
  - Informe nome, dosagem, dias da semana e horários.
  - Adicione múltiplos horários e visualize a lista organizada automaticamente.
  - O app enviará notificações nos horários definidos.

  ![Tela Adicionar Lembrete](https://github.com/user-attachments/assets/436d0c99-618a-4dae-a223-ba87770a20fe)


# 7. Requisitos do sistema

### Para Usuários (App Instalado)
  - Android 7.0 (API 24) ou superior.
  - Internet necessária apenas para instalação (funcionamento é offline).
  - Permissão para notificações.

### Para Desenvolvedores
- Android Studio Arctic Fox (2020.3.1) ou superior.
- JDK 11 ou superior.
- Kotlin configurado.
  
Dependências principais:
- Room
- Navigation Component
- ViewModel + LiveData
- RecyclerView
- Material Components
