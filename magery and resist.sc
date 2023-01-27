sub magery_resist_healing_self()
   # ������ �� ���� ����������
   # c 33.3 �� 46 ������� ������ � ����
   # c 46 �� 66 ������� ���� � ����
   # c 66 �� 86 ������� ������ � ����
   # c 86 �� 100 ������� �� � ����
   # ���� Magery = false, ����� ������ Magic Resistance
   # ���� Magery = true, ����� ������ Magery
   # ����� ������ ����� �� ���, ����� � ������ ���� ����� �� ���
   # ������� ID ����� ��� ����� ����
   VAR Magery = true
   VAR SumkaRegov = '0x50EBD5FA'
   # ���� ������ �� ������
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
            UO.charprint(UO.GetSerial(), '38', '��� ������ �� ����')
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

# ������� �������� ����� � ������� � ����� ����� �� ���� (������)
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
            UO.charprint(UO.GetSerial(), '38', '��� ����� � �����')
            return 5
         endif
      endif
   next
   uo.cast(magery,'self')
   wait(wait)
end sub
# ������� �������� ����� � ������� � ����� ����� �� ���� (�����)