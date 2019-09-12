using Microsoft.EntityFrameworkCore;
using System;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class AreaAtendimentoMap : IEntityTypeConfiguration<AreaAtendimento>
    {
        public void Configure(EntityTypeBuilder<AreaAtendimento> entity)
        {
            entity.HasKey(e => e.CdAreaatendimento);

            entity.ToTable("area_atendimento");

            entity.Property(e => e.CdAreaatendimento).HasColumnName("cd_areaatendimento");

            entity.Property(e => e.CdAreaatuacao).HasColumnName("cd_areaatuacao");

            entity.Property(e => e.DsAreaatendimento)
                .IsRequired()
                .HasColumnName("ds_areaatendimento")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DsEmail)
                .IsRequired()
                .HasColumnName("ds_email")
                .HasColumnType("character varying(255)");

            entity.Property(e => e.DtAtualizacao)
                .HasColumnName("dt_atualizacao")
                .HasColumnType("date");

            entity.Property(e => e.DtCadastro)
                .HasColumnName("dt_cadastro")
                .HasColumnType("date");

            entity.HasOne(d => d.CdAreaatuacaoNavigation)
                .WithMany(p => p.AreaAtendimento)
                .HasForeignKey(d => d.CdAreaatuacao)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("area_atuacao_area_atendimento_fk");
        }
    }
}
