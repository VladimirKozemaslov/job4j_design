insert into rights(name) values ('CREATE'), ('SELECT'), ('INSERT'), ('UPDATE'), ('DELETE');

insert into roles(name) values ('Manager'), ('Head manager'), ('Admin');

insert into rights_roles(role, c_right) values
((select id from roles where name = 'Manager'), (select id from rights where name = 'SELECT')),
((select id from roles where name = 'Manager'), (select id from rights where name = 'UPDATE')),
((select id from roles where name = 'Head manager'), (select id from rights where name = 'SELECT')),
((select id from roles where name = 'Head manager'), (select id from rights where name = 'INSERT')),
((select id from roles where name = 'Head manager'), (select id from rights where name = 'UPDATE')),
((select id from roles where name = 'Head manager'), (select id from rights where name = 'DELETE')),
((select id from roles where name = 'Admin'), (select id from rights where name = 'CREATE')),
((select id from roles where name = 'Admin'), (select id from rights where name = 'SELECT')),
((select id from roles where name = 'Admin'), (select id from rights where name = 'INSERT')),
((select id from roles where name = 'Admin'), (select id from rights where name = 'UPDATE')),
((select id from roles where name = 'Admin'), (select id from rights where name = 'DELETE'));

insert into users(name, role) values
('Bill Clinton', (select id from roles where name = 'Manager')),
('George Bush', (select id from roles where name = 'Manager')),
('Barak Obama', (select id from roles where name = 'Manager')),
('Donald Trump', (select id from roles where name = 'Manager')),
('Joe Biden', (select id from roles where name = 'Head manager')),
('Vladimir Putin', (select id from roles where name = 'Admin'));

insert into categories(name) values
('Cleaning'),
('Repair'),
('Animal care'),
('Babysitting');

insert into states(name) values
('Opened'), ('In Action'), ('Closed'), ('Cancelled');

insert into items(name, c_user, category, state) values
    ('Clean room',
        (select id from users where name = 'Barak Obama'),
        (select id from categories where name = 'Cleaning'),
        (select id from states where name = 'In Action')
    ),
    ('Walk the dog',
        (select id from users where name = 'Bill Clinton'),
        (select id from categories where name = 'Animal care'),
        (select id from states where name = 'Closed')
    ),
    ('Washing machine is broken',
        (select id from users where name = 'George Bush'),
        (select id from categories where name = 'Repair'),
        (select id from states where name = 'Opened')
    ),
    ('Need babysitter',
        (select id from users where name = 'Joe Biden'),
        (select id from categories where name = 'Babysitting'),
        (select id from states where name = 'Cancelled')
    );

insert into comments(text, item) values
('You must walk at least 3 km', (select id from items where name = 'Walk the dog')),
('Machine mark is Indesit XW321Y', (select id from items where name = 'Washing machine is broken'));

insert into attachments(content, item) values
('--- room photo ---', (select id from items where name = 'Clean room')),
('--- baby photo ---', (select id from items where name = 'Need babysitter'));