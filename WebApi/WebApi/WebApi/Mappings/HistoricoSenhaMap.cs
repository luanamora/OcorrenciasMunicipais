using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class HistoricoSenhaMap : IEntityTypeConfiguration<HistoricoSenha>
    {
        public void Configure(EntityTypeBuilder<HistoricoSenha> entity)
        {
            entity.HasKey(e => e.CdHistoricosenha);

            entity.ToTable("historico_senha");

            entity.Property(e => e.CdHistoricosenha)
                .HasColumnName("cd_historicosenha")
                .HasDefaultValueSql("nextval('historico_senha_cd_senha_seq'::regclass)");

            entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

            entity.Property(e => e.DsSenha)
                .IsRequired()
                .HasColumnName("ds_senha")
                .HasColumnType("character varying(30)");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.HasOne(d => d.CdUsuarioNavigation)
                .WithMany(p => p.HistoricoSenha)
                .HasForeignKey(d => d.CdUsuario)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("usuario_historico_senha_fk");
        }
    }
}
