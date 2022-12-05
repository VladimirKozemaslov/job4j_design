begin;
declare cur_prod scroll cursor for
    select * from products;

move last from cur_prod;

FETCH next FROM cur_prod;

FETCH prior FROM cur_prod;

move backward 4 from cur_prod;

FETCH prior FROM cur_prod;

move backward 7 from cur_prod;

FETCH prior FROM cur_prod;

move backward 4 from cur_prod;

FETCH prior FROM cur_prod;

FETCH prior FROM cur_prod;

close cur_prod;

commit;