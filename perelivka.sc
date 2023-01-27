sub perelivka()
# Переливает ману, куре, стаму, пойзон, шринки из кега в другой кег.
# Чтобы остановить переливку напишите чаром одно из слов: STOP, stop, стоп, СТОП   
   VAR potion
   UO.charprint(UO.GetSerial(), '172','Кег откуда наливаем?')
   UO.exec('addobject keg1')
   while uo.targeting() 
      wait(100) 
   wend
   UO.charprint(UO.GetSerial(), '172','Кег куда заливаем?')
   UO.exec('addobject keg2')
   while uo.targeting() 
      wait(100) 
   wend
   UO.DeleteJournal()
   UO.click(UO.GetSerial('keg1'))
   wait(500)
   if UO.InJournal('mana') then
      potion = '0x0F08'
   else if UO.InJournal('refresh') then
      potion = '0x0F0B'
   else if UO.InJournal('cure') then
      potion = '0x0F07'
   else if UO.InJournal('poison') then
      potion = '0x0F0A'
   else if UO.InJournal('shrink') then
      potion = '0x0F06'
   end if
   Repeat
      UO.findtype('0x0F0E',-1,'backpack')
      if uo.findcount() then
         UO.moveitem('finditem',1,UO.GetSerial('keg1'))
         wait(888)
         UO.findtype('potion',-1,'backpack')
         UO.moveitem('finditem',1,UO.GetSerial('keg2'))
         wait(888)
      else
         UO.Print('No have empty bottles')
         return
      endif  
   until UO.InJournal('stop|STOP|стоп|СТОП|keg is full')
end sub