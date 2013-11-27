# language: pt
Funcionalidade: Validar Servico de Insercao de Afiliado

Esquema do Cenario: Insercao de afiliado existente na tabela user_all usando servico default
    Dado que exista na tabela user_all um usuario cujo idt_person seja igual a <idt_person>
	Dado que nao exista nas tabelas user_supplier, usersupp_commevensourgrou e user_supplier_parent um usuario cujo idt_person seja igual a <idt_person>
	Quando efetuar uma chamada ao servico default para inserir um usuario cujo idt_person seja igual a <idt_person>
	Entao validar que a reposta do servico e o codigo <codigo>
	E validar que o usuario cujo idt_person seja igual a <idt_person> foi inserido nas tabelas user_supplier e usersupp_commevensourgrou

    Exemplos: 
    | idt_person  | codigo |  
    | <131783833> | <success> |

Esquema do Cenario: Insercao de afiliado existente na tabela user_all usando servico assincrono
    Dado que exista na tabela user_all um usuario cujo idt_person seja igual a <idt_person>
	Dado que nao exista nas tabelas user_supplier, usersupp_commevensourgrou e user_supplier_parent um usuario cujo idt_person seja igual a <idt_person>
	Quando efetuar uma chamada ao servico assincrono para inserir um usuario cujo idt_person seja igual a <idt_person>
	Entao validar que a reposta do servico e o codigo <codigo>
	E validar que o usuario cujo idt_person seja igual a <idt_person> foi inserido nas tabelas user_supplier e usersupp_commevensourgrou
	
    Exemplos: 
    | idt_person  | codigo |  
    | <131783833> | <success> |     