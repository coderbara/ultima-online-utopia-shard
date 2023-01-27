# Перекладывает однотипные вещи из одной сумки в другую.
# Можно перекладывать из бекпака в сумку или наоборот.

sub pereklad()
   UO.charprint(UO.GetSerial(), '172','Откуда переложить?')
   UO.exec('addobject MyBag')
   while uo.targeting() 
      wait(100) 
   wend
   UO.charprint(UO.GetSerial(), '172','Что переложить?')
   UO.exec('addobject MyItem')
   while uo.targeting() 
      wait(100) 
   wend
   UO.charprint(UO.GetSerial(), '172','Сумка куда cкладываем однотипные вещи?')
   UO.exec('addobject ContainerReceiver')
   while uo.targeting() 
      wait(100) 
   wend
   UO.useobject(UO.GetSerial('MyBag'))
   UO.useobject(UO.GetSerial('ContainerReceiver'))
   VAR i
   for i = 0 to 1000
      UO.DeleteJournal()
      UO.findtype(uo.getgraphic('MyItem'),-1,UO.GetSerial('MyBag'))
      if uo.findcount() then
         UO.MoveItem('finditem', '1', UO.GetSerial('ContainerReceiver'), '100', '100', '0')
         wait(10)
      else
         i = 1000    
      end if
      if UO.InJournal('container is full') then
         i = 1000
         UO.charprint(UO.GetSerial(), '172','Контейнер заполнен')    
      end if
   next
   UO.charprint(UO.GetSerial(), '172','Закончили перекладывать')
endsub
