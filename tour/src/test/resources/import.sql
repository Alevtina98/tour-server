INSERT INTO tour.tour (id, name, "desc", code, code_js, form_name, form_caption, is_general_user)
VALUES (-1, 'Название', 'Описание', 'xml', 'js', 'MainForm',  'MainForm', true);

INSERT INTO tour.tour (id, name, "desc", code, code_js, create_date, change_date, form_name, form_caption, is_general_user)
VALUES (-2, 'Название2', 'Описание2', 'xml2', 'js2', 'Form2',  'Form2', true);

INSERT INTO tour.tour (id, name, "desc", code, code_js, create_date, change_date, form_name, form_caption, is_general_user)
VALUES (-3, 'Название3', 'Описание3', 'xml3', 'js3', 'Form3',  'Form3', true);

INSERT INTO tour.tour (id, name, "desc", code, code_js, create_date, change_date, form_name, form_caption, is_general_user)
VALUES (-4, 'Название4', 'Описание4', 'xml4', 'js4', 'Form4',  'Form4', true);

INSERT INTO tour.tour (id, name, "desc", code, code_js, create_date, change_date, form_name, form_caption, is_general_user)
VALUES (-5, 'Название5', 'Описание5', 'xml5', 'js5', 'Form5',  'Form5', true);

INSERT INTO tour.user_tour (tour_id, user_id, status)
VALUES (-1, 'User1', 'назначен');
INSERT INTO tour.user_tour (tour_id, user_id)
VALUES (-2, 'User1', 'назначен');
INSERT INTO tour.user_tour (tour_id,user_id, status)
VALUES (-5, 'User2', 'отложен');
INSERT INTO tour.user_tour (tour_id,user_id, status)
VALUES (-2, 'User2', 'прерван');