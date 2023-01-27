sub magery_resist_healing_self()
   # Кидает на себя заклинания
   # c 33.3 до 46 кастует Пойзон в себя
   # c 46 до 66 кастует Лайт в себя
   # c 66 до 86 кастует Карпор в себя
   # c 86 до 100 кастует ФС в себя
   # Если Magery = false, будет качать Magic Resistance
   # Если Magery = true, будет качать Magery
   # Бинты кинуть рядом на пол, сумку с регами тоже рядом на пол
   # Указать ID сумки где лежат реги
   VAR Magery = true
   VAR SumkaRegov = '0x50EBD5FA'
   # Ниже ничего не меняем
   VAR MaxHP = uo.GetHP()
   VAR MaxMP = uo.Mana
   VAR check = 0
   UO.useobject(SumkaRegov)
   wait(500)
   while true
      if uo.GetHP() >= MaxHP - 5 then
         if uo.mana >= MaxMP - 5 then
            if Magery then
               if UO.SkillVal('Magery') < 460 then
                  check = checkRegsAndCast(SumkaRegov, 'Poison', 2500, '0x0F88', '0x0F88')
               else if UO.SkillVal('Magery') < 660 then
                  check = checkRegsAndCast(SumkaRegov, 'Lightning', 2500, '0x0F8C', '0x0F86')
               else if UO.SkillVal('Magery') < 860 then
                  check = checkRegsAndCast(SumkaRegov, 'Energy Bolt', 3000, '0x0F88', '0x0F7A')
               else if UO.SkillVal('Magery') < 1000 then
                  check = checkRegsAndCast(SumkaRegov, 'Flame Strike', 4000, '0x0F8C', '0x0F8D')
               else
                  return
               endif
               if check == 5 then
                  return
               endif 
            else
               if UO.SkillVal('Magic Resistance') < 1000 then
                  checkRegsAndCast(SumkaRegov, 'Magic Arrow', 1500, '0x0F8C', '')
               else
                  return
               endif 
            endif
         else
            UO.UseSkill('Meditation')
            wait(5100)   
         endif
      else
         if UO.CountGround('0x0E21') then
            UO.Waittargetobject('self')
            UO.UseFromGround('0x0E21')
            wait(3500)
         else
            UO.charprint(UO.GetSerial(), '38', 'Нет бинтов на полу')
            return   
         endif
         UO.FindType('0x0E20',-1,'backpack')
         if uo.findcount() then
            UO.MoveItem('finditem', 0, SumkaRegov)
            wait(10)
         endif
      endif
   wend  
end sub

# Функция Проверки регов в бекпаке и каста магии на себя (начало)
sub checkRegsAndCast(sumka, magery, wait, reg1, reg2)
   DIM regs[2]
   regs[0] = reg1
   regs[1] = reg2 
   VAR i 
   for i = 0 to 1
      UO.findtype(regs[i],-1, 'backpack')
      if not uo.findcount() then
         UO.findtype(regs[i],-1, sumka)
         if uo.findcount() then
            UO.Grab('1', 'finditem')
            wait(10)
         else
            UO.charprint(UO.GetSerial(), '38', 'Нет регов в сумке')
            return 5
         endif
      endif
   next
   uo.cast(magery,'self')
   wait(wait)
end sub
# Функция Проверки регов в бекпаке и каста магии на себя (конец)