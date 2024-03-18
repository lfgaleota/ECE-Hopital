create table if not exists employe
(
    numero  decimal(4) primary key,
    nom     varchar(12) not null,
    prenom  varchar(12) not null,
    adresse varchar(40),
    tel     char(14),
    unique (nom, prenom, tel)
);

create table if not exists docteur
(
    numero     decimal(4) primary key,
    specialite enum ('Anesthesiste',
        'Cardiologue',
        'Generaliste',
        'Orthopediste',
        'Pneumologue',
        'Radiologue',
        'Traumatologue') default 'Generaliste' not null,
    foreign key (numero) references employe (numero) on delete cascade on update cascade
);

create table if not exists service
(
    code      char(3) primary key,
    nom       varchar(30) not null unique,
    batiment  char(1)     not null,
    directeur decimal(4),
    foreign key (directeur) references docteur (numero) on delete set null on update cascade
);

create table if not exists infirmier
(
    numero       decimal(4) primary key,
    code_service char(3),
    rotation     enum ('JOUR', 'NUIT') not null default 'JOUR',
    salaire      decimal(6, 2),
    foreign key (numero) references employe (numero) on delete cascade on update cascade,
    foreign key (code_service) references service (code) on delete set null on update cascade
);

create table if not exists chambre
(
    code_service char(3),
    no_chambre   decimal(3),
    primary key (code_service, no_chambre),
    surveillant  decimal(4),
    nb_lits      decimal(2),
    foreign key (code_service) references service (code) on delete cascade on update cascade,
    foreign key (surveillant) references infirmier (numero) on delete set null on update cascade
);

create table if not exists malade
(
    numero   decimal(4) primary key,
    nom      varchar(12) not null,
    prenom   varchar(12) not null,
    adresse  varchar(40),
    tel      char(14),
    mutuelle varchar(6),
    unique (nom, prenom, tel)
);

create table if not exists hospitalisation
(
    no_malade    decimal(4) primary key,
    code_service char(3)    not null,
    no_chambre   decimal(3) not null,
    lit          decimal(2),
    unique (code_service, no_chambre, lit),
    foreign key (no_malade) references malade (numero) on delete cascade on update cascade,
    foreign key (code_service) references service (code) on delete cascade on update cascade,
    foreign key (code_service, no_chambre) references chambre (code_service, no_chambre) on delete cascade on update cascade
);

create table if not exists soigne
(
    no_docteur decimal(4) not null,
    no_malade  decimal(4) not null,
    primary key (no_docteur, no_malade),
    foreign key (no_docteur) references docteur (numero) on delete cascade on update cascade,
    foreign key (no_malade) references malade (numero) on delete cascade on update cascade
);
